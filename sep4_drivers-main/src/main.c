#include "wifi.h"
#include "uart.h"
#include "soil.h"
#include <util/delay.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <avr/interrupt.h>

#define SERVER_IP "https://iot-service-api.gentlepond-0bd2f955.northeurope.azurecontainerapps.io/api/IoT/SoilHumidity"

static uint8_t _buff[100];
static uint8_t _index = 0;
volatile static bool _done = false;
void console_rx(uint8_t _rx)
{
    uart_send_blocking(USART_0, _rx);
    if(('\r' != _rx) && ('\n' != _rx))
    {
        if(_index < 100-1)
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
//        uart_send_string_blocking(USART_0, (char*)_buff);
    }
}

// Funktion til at sende HTTP POST
void send_http_post(const char* url, const char* data) {
    wifi_command_create_TCP_connection(url, 80, NULL, NULL); // Opret forbindelse
    wifi_command_TCP_transmit((uint8_t*)data, strlen(data)); // Send data
}

int main()
{
    char welcome_text[] = "Welcome from SEP4 IoT hardware!\n";
    char prompt_text[] = "Type text to send: ";

    uart_init(USART_0, 9600, console_rx);
    wifi_init();
    soil_sensor_init();

    sei();

    wifi_command_join_AP("TASKALE", "cen7936219can");

    //hvis du skal forbinde lokalt
    /*int result = wifi_command_create_TCP_connection("192.168.0.230", 5000, NULL, NULL);
    if (result == 0) {
        uart_send_string_blocking(USART_0, "Connected to Server!\n");
    } else {
        uart_send_string_blocking(USART_0, "FAILED to connect!\n");
    }*/

    wifi_command_TCP_transmit((uint8_t*)welcome_text, strlen(welcome_text) );
    uart_send_string_blocking(USART_0, prompt_text);

    while(1)
    {
        // Læs fugtighedsværdien fra sensor
        uint8_t humidity = soil_sensor_read();

        // Opret JSON-data til POST-anmodningen
        char payload[50];
        sprintf(payload, "{\"soil_humidity_value\": %d}", humidity);

        // Send data til Spring Boot server
        send_http_post("https://iot-service-api.gentlepond-0bd2f955.northeurope.azurecontainerapps.io/api/IoT/SoilHumidity", payload);

        if (_done)
        {
            wifi_command_TCP_transmit(_buff, strlen((char*)_buff));
            _done = false;
            uart_send_string_blocking(USART_0, prompt_text);
        }

        _delay_ms(10000); //  10 sekunder
    }

    return 0;
}