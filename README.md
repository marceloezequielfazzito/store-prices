# store-prices

Retrieve product prices by product id , brand id and date

## How to use

```bash

curl -XGET -H "Content-type: application/json" 'http://[server]/price/[product id]?brandId=[brand id]&date[dd-MM-yyyy'T'HH:mm:ss]'

response 

{
    "product_id": number,
    "brand_id": number,
    "price_list": number,
    "start_date": string format [dd-MM-yyyy'T'HH:mm:ss],
    "end_date": string format [dd-MM-yyyy'T'HH:mm:ss],
    "price": number,
    "currency": string 
}

example 

curl -XGET -H "Content-type: application/json" 'http://127.0.0.1:8080/price/35455?brandId=1&date=14-06-2020T19:00:00'

response 

{
    "product_id": 35455,
    "brand_id": 1,
    "price_list": 1,
    "start_date": "14-06-2020T00:00:00",
    "end_date": "31-12-2020T23:59:59",
    "price": 35.5,
    "currency": "EUR"
}



```


## Installation

Use java 14 and maven , execute maven clean install command on project root directory

```bash
mvn clean install
```

it will create store-prices.jar executable jar file inside /target folder


## Running in BASH

```bash

cd target 

java -jar store-prices.jar 

```

the server will start at port 8080 

## Running in DOCKER

on project root directory

```bash

docker build -t [image-name]  .

docker run -d -p 8080:8080 [image-name]
 
```
docker will expose the port 8080 in the local machine  
