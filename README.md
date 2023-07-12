# ReMarket

Geekbrains.Java.level05  

============================================================   
ReMarket -- итоговый проект обучения в Geekbrains  

WebMarket  
Backend - java  
Frontend - angular(в минималистичном виде)  

Микро-сервисная архитектура
6 MS == 

Корзина хранится в Redis
База продуктов - в памяти
База юзеров - файл в корне проекта


\
\
-============================================================   
### Swagger UI  

CoreMS  
http://localhost:8189/web-market-core/swagger-ui/index.html  

CartMS  
http://localhost:8166/web-market-carts/swagger-ui/index.html  

AuthMS
http://localhost:8188/web-market-auth/swagger-ui/index.html


\
\
============================================================   
### PayPal  
Без данных в файле secret.properties - проект не запускается  
Можно подставить **любые** значения для id/secret

Подставить свои данные в файлы:  
1--  
../core-service/src/main/resources/secret.properties  
`paypal.client-id= ***** `  
`paypal.client-secret= ***** `  

2--  
client-id  
ReMarket/front-service/src/main/resources/static/index.html  
`<script src="https://www.paypal.com/sdk/js?client-id=*****&currency=RUB"></script>`



\
\
============================================================  
Этот проект - продолжения развития учебного проекта:

GB.Java.05.SpringWeb-2.20211227  
https://github.com/RRenat358/2121.GB.Java.05.SpringWeb-2.20211227.git

\
\
============================================================





















\
\
\
\
\
\


