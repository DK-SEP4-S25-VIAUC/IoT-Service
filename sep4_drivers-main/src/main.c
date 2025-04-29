#include "wifi.h"
#include "uart.h"
#include "soil.h"
#include <util/delay.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <avr/interrupt.h>

static uint8_t _buff[100];
static uint8_t _index = 0;
volatile static bool _done = false;

void console_rx(uint8_t _rx)
{
    uart_send_blocking(USART_0, _rx);
    if (('\r' != _rx) && ('\n' != _rx))
    {
        if (_index < 100 - 1)
        {
            _buff[_index++] = _rx;
        }
    }
    else
    {
        _buff[_index] = '\0';
        _index = 0;
        _done = true;
        uart_send_blocking(USART_0, '\n');
    }
}

int main()
{
    char soil_text[50];

    uart_init(USART_0, 9600, console_rx);
    uart_send_string_blocking(USART_0, "Booting...\r\n");

    wifi_init();
    uart_send_string_blocking(USART_0, "WiFi init OK\r\n");

    soil_sensor_init();
    uart_send_string_blocking(USART_0, "Soil sensor init OK\r\n");

    sei();

    uart_send_string_blocking(USART_0, "Connecting to WiFi...\r\n");
    wifi_command_join_AP("TASKALE70", "cen7936219can");
    uart_send_string_blocking(USART_0, "WiFi connected!\r\n");

    uart_send_string_blocking(USART_0, "Connecting to TCP server...\r\n");
    wifi_command_create_TCP_connection("4.207.206.251", 5000, NULL, NULL);
    uart_send_string_blocking(USART_0, "TCP connection established!\r\n");

    wifi_command_TCP_transmit((uint8_t*)"Welcome from SEP4 IoT hardware!\n", 32);

    while (1)
{
    if (_done)
    {
        wifi_command_TCP_transmit(_buff, strlen((char*)_buff));
        uart_send_string_blocking(USART_0, "Sent user input via TCP\r\n");
        _done = false;
    }

    uint8_t humidity = soil_sensor_read();
    sprintf(soil_text, "{\"soil_humidity_value\":%d}\r\n", humidity);

    wifi_command_TCP_transmit((uint8_t*)soil_text, strlen(soil_text));
    uart_send_string_blocking(USART_0, "Sent: ");
    uart_send_string_blocking(USART_0, soil_text);

    _delay_ms(3600000);
}

    return 0;
}
