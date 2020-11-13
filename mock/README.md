### Реализация soap-клиента асинхронного сервиса open-api

## скачайте приложение SOAPUI адресу:
 https://www.soapui.org/downloads/soapui/ 
 скачивать программу: soapui open source

## установите soapui

## Запустите mock-сервисы в SOAPUI
1. в Приложении soap ui нажмите курсором мыши на иконку import
2. Выберите мок файлы, 
        сначало /mock/mock-AuthService-soapui-project
        потом /mock/mock_AsyncService-soapui-project

## Запустите виртуальные сервисы на своем компьютере:
найдите в soapui элементы: 
1. OpenApiAsyncMessageConsumerServiceSoapBinding MockService
2. OpenApiMessageConsumerServiceSoapBinding MockService
нажмите по ним левым курсором мыши
и выберите: start Minimised

## Как проверить, что сервисы запущены:
http://localhost:8087/mock/consumer/authService - виртуальный сервис 
                                                        авторизации

http://localhost:8086/mock/consumer/AsyncService - виртуальный сервис 
                                                    ответа по РНПТ

если в браузере запускаются данные адреса и нет ошибок значит виртуальные сервисы запущены

  думаю понятно, что 8087 и 8086 это номера портов. если у вас они заняты, то запустится виртуальный сервис на другом порте,  порт можно посмотреть в soapui

 
