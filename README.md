# spring-security-demo
Spring Boot Project for Login/Register functionality using Spring Security with JWT.

## How to Run the Project :-
1. Download or clone the repository on your machine.
2. Extract the .zip file
3. Import the extracted project to STS or Eclipse.
4. Do Maven Update so that All the dependencies will be downloaded.
5. Open `SpringSecurityDemoApplication.java` file and do run as Java Application.

## How to Test the Project :-
1. Open Postman(Please install it if you don't have it).
2. Then try to hit `localhost:9090/auth/register` url with below request body:-
```javascript
{
	"id": "101",
	"email": "vk@gmail.com",
	"password": "vivek",
	"fullname": "Vivek Kurmi"
}
```
If you get the response like:
```javascript
{
    "message": "User registered successfully"
}
```
It means user has been registerd successfully.
3. Now to login try to hit `localhost:9090/auth/login` with below request body:-
```javascript
{
	"email": "vk@gmail.com",
	"password": "vivek"
}
```
Then you will get response like:-
```javascript
{
    "username": "vk@gmail.com",
    "token": <TOKEN>"
}
```
Copy <TOKEN> whatever comes in the token
4. Now to get `products` try to hit `localhost:9090/products` by putting headers like:-
``javascript
Authorization: Bearer <TOKEN>,
```
