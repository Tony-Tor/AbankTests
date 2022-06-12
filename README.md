# AbankTests
A test task for A Bank that gives a gif emotion mood relative to the USD exchange rate and the selected currency.

Build and Instalation 
=
Perform the following steps before starting:

Tests Gradle tasks:verification:test  

Create application.properties
Build Gradle tasks:build:bootJar

Create and connect a Docker daemon

Run ./docker-compose.yml;

Settings
=

For the test scope, application_test.properties is used. For deploy scope, application.properties is used.


Setting list:

|setting|Description|
|:---------------------------------------------------------------|:-------------------------------------|
|application.url.gifs=https://i.giphy.com/|Link to the giphy image|
|application.url.brokeRich=https://api.giphy.com/v1/gifs/random|Link to search by tag|
|application.url.exchangeRate=https://openexchangerates.org/api/|Rest API for getting currency exchange rate information|
|application.app_id.giphy=your test appid|your test appid for giphy|
|application.app_id.openexchange=your test appid|your test appid for openexchange|
|application.setting.tag.rich=rich|Tag for a positive change in the currency exchange rate|
|application.setting.tag.broke=broke|Tag for a negative change in the currency exchange rate|
|application.setting.tag.not_found=error404|Tag for NotException|
|application.setting.rating=g|Raiting MPA|

Using
=

Using the service is very simple, you only need to substitute the value from the table in the {currency} field

|Endpoint|Description|
|:---|:---|
|/benefits/{currency}|Getting gif-emotions for {currency} exchange rate|

Possible {currency} values:

|AED|CLP|HRK|MDL|RUB|UYU|
|:---|:---|:---|:---|:---|:---|
|AFN|CNH|HTG|MGA|RWF|UZS|
|ALL|CNY|HUF|MKD|SAR|VES|
|AMD|COP|IDR|MMK|SBD|VND|
|ANG|CRC|ILS|MNT|SCR|VUV|
|AOA|CUC|IMP|MOP|SDG|WST|
|ARS|CUP|INR|MRU|SEK|XAF|
|AUD|CVE|IQD|MUR|SGD|XAG|
|AWG|CZK|IRR|MVR|SHP|XAU|
|AZN|DJF|ISK|MWK|SLL|XCD|
|BAM|DKK|JEP|MXN|SOS|XDR|
|BBD|DOP|JMD|MYR|SRD|XOF|
|BDT|DZD|JOD|MZN|SSP|XPD|
|BGN|EGP|JPY|NAD|STD|XPF|
|BHD|ERN|KES|NGN|STN|XPT|
|BIF|ETB|KGS|NIO|SVC|YER|
|BMD|EUR|KHR|NOK|SYP|ZAR|
|BND|FJD|KMF|NPR|SZL|ZMW|
|BOB|FKP|KPW|NZD|THB|ZWL|
|BRL|GBP|KRW|OMR|TJS| |
|BSD|GEL|KWD|PAB|TMT| |
|BTC|GGP|KYD|PEN|TND| |
|BTN|GHS|KZT|PGK|TOP| |
|BWP|GIP|LAK|PHP|TRY| |
|BYN|GMD|LBP|PKR|TTD| |
|BZD|GNF|LKR|PLN|TWD| |
|CAD|GTQ|LRD|PYG|TZS| |
|CDF|GYD|LSL|QAR|UAH| |
|CHF|HKD|LYD|RON|UGX| |
|CLF|HNL|MAD|RSD|USD| |
