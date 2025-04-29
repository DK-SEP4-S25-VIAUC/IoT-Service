#include "soil.h"
#include "wifi.h"    
#include <avr/io.h>
#include <util/delay.h>
#include <stdio.h>
#include <string.h>

static uint16_t read_adc_value(void)
{
    ADCSRA |= (1 << ADSC); 
    while (ADCSRA & (1 << ADSC)); 
    return ADC; 
}

void soil_sensor_init(void)
{
    ADMUX = (1 << REFS0) | (0b0101); // Brug 5V reference, Kanal 5 (A5)
    ADCSRA = (1 << ADEN) | (1 << ADPS2) | (1 << ADPS1); // Enable ADC, prescaler 64
}

void send_http_post(const char* url, const char* data) {
    // Opret forbindelse og send HTTP POST anmodning til Spring Boot API
    wifi_command_create_TCP_connection(url, 80, NULL, NULL);  // Standard port HTTP 80
    wifi_command_TCP_transmit((uint8_t*)data, strlen(data));  // Send data som HTTP POST
}

void soil_sensor_read(void)
{
    uint16_t adc_value = read_adc_value();  // Læs værdien fra ADC
    const uint16_t dry_value = 450;        // Værdi for tør jord (målt tidligere)
    const uint16_t wet_value = 188;        // Værdi for våd jord (målt tidligere)

    // Beregn fugtighed som procent
    int16_t humidity = (dry_value - adc_value) * 100 / (dry_value - wet_value);

    // Begræns fugtighedsværdien mellem 0 og 100
    if (humidity < 0) humidity = 0;
    if (humidity > 100) humidity = 100;

    return humidity;
}
