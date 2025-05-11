#include "soil.h"
#include <avr/io.h>
#include <stdint.h>

/* Læs én ADC-prøve på kanal A5 */
static uint16_t read_adc_value(void)
{
    ADCSRA |= (1 << ADSC);            /* start konvertering            */
    while (ADCSRA & (1 << ADSC)) {}   /* vent til færdig               */
    return ADC;
}

void soil_sensor_init(void)
{
    ADMUX  = (1 << REFS0) | 0b00000101;          /* 5 V ref, kanal 5 (A5)      */
    ADCSRA = (1 << ADEN)  | (1 << ADPS2) | (1 << ADPS1); /* Enable, ÷64 presc. */
}

uint8_t soil_sensor_read(void)
{
    const uint16_t dry = 450;     /* kalibrér disse to værdier efter */
    const uint16_t wet = 188;     /* egne målinger              */

    int16_t raw = read_adc_value();
    int16_t hum = (dry - raw) * 100 / (dry - wet);

    if (hum < 0)   hum = 0;
    if (hum > 100) hum = 100;

    return (uint8_t)hum;
}
