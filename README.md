#Globant interview API
#Sample REST API for testing purpose

##Requirements
1) Build a small app that consists of 4 endpoints.

Build an endpoint that authenticates a user based on a login/password passed in a JSON payload and verifies against a simple data structure (Mongo, MySQL, etc.).

Build an endpoint that returns all users in the database filtered by a URL parameter (can be city, profession, etc) and groups them by another parameter (also your choice).

Build an endpoint that checks and returns the status of all components that it depends on (e.g. Is Mongo still up OK, etc.).

Build an endpoint that when called returns the list of files in a given directory.

This is a sample REST API app using MySQL, Java and jersey Framework.<br>
You need MySQL, Jersey framework jar files and eclipse or netbeans to run this project.<br>
I have used maven to create project, it helped me to save time and also given me proper structure and jars. <br>
You guys can find details here, it’s a youtube video <br>

https://youtu.be/skltzZH7i4w<br>
There are multiple ways to do it you can either create dynamic web project and also download jersery jar manually using hibernate connection.<br>

http://www.mkyong.com/hibernate/quick-start-maven-hibernate-mysql-example/<br>
Choice is upto you guys, but best way to do it is maven project.<br>
There are certain things you need to install for using this app <br>
1. Postman(https://www.getpostman.com/)<br>
2. Eclipse(https://eclipse.org/downloads/)<br>
3. MySQL<br>

#Need to create certain tables in database<br>

//Please check SQL file i have added into project and just runinto your database<br>

#####Things to do before running API.<br>
1) Check MySQL is working and server is running. <br>
2) Add database and tables which is added into project<br>
3) Download the project from github or driectly add through eclipse <br>
4) check for all jar files <br>
5) insatll tom-cat server (7 or 8).<br> 
6) Run the project, project may give servelt error but jersery framwork will handle it. <br>
7) if u cant find maven project or speific project inside please check given tutorial. <br>
8) Download postman and register with it. <br>
9) You are ready to run, mostly you will not face any problems. <br>

####Endpoint Definitions
#####1)User Register: [POST] /webapi/Users/Create
```json
payload:
{
    "username": "########",
    "password": "########"
}
```
#####Json response
```json
{
  "city": "Dallas",
  "count": 0,
  "createdBy": "Test1",
  "createdOn": "2016-03-15 03:19:57.0",
  "errorist": [],
  "gender": "Male",
  "givenName": "Test1",
  "profession": "Engg",
  "state": "Tx",
  "updateOn": "2016-03-15 03:19:57.0",
  "updatedBy": "Test1",
  "userId": 25,
  "username": "raj29"
}
```
#####2)User Login: [POST] /webapi/Users/Login
```json
payload:
{
    "username": "########",
    "password": "########"
}
```
#####Json response
```json
{
  "city": "Dallas",
  "count": 0,
  "createdBy": "Test1",
  "createdOn": "2016-03-15 02:34:55.0",
  "errorist": [
    {
      "expEndTime": 1458030622523,
      "expStartTime": 1458028822523,
      "redirectURL": "http://localhost:8080/RestTest/webapi/Users/raj25",
      "tokennumber": "32L4N77TTO8B9S1CD"
    }
  ],
  "gender": "Male",
  "givenName": "Test1",
  "profession": "Engg",
  "state": "Tx",
  "updateOn": "2016-03-15 02:34:55.0",
  "updatedBy": "Test1",
  "userId": 24,
  "username": "raj25"
}
```
#####3)User Filter/Pagination: [GET] /webapi/Users

there are various way you can use this


#####Example of filtering and pagination:-
```json
http://localhost:8080/RestTest/webapi/Users?city=Arlington&state=TX&profession=engg&Groupby=Gender&size=1&start=0
```
######Note:- This will be and opration between columns and it will do group by based on passed coloumn value..

#####Example of pagaination

Size= Number of elements you wan to disply Start=number of position you want to start from.

```json
http://localhost:8080/RestTest/webapi/Users?size=1&start=0
```
Just by calling above link you can get all the users ..

#####4)User Check all components : [GET] /webapi/Users/Check

This endpoint will check the mysql is running or not, it will call the connection class and check its up or not

#####5)User Check all components : [POST] /webapi/Users/GetFiles

Give the path and it return alll the list of files present in Directory
```json
payload:
{
    "fileParentPath": "E:/^329 Eclipse/eclipse"
}
```
#####Json response
```json
[
  {
    "fileSize": "60 bytes ",
    "fileType": "File",
    "filename": ".eclipseproduct"
  },
  {
    "fileSize": "275898 bytes ",
    "fileType": "File",
    "filename": "artifacts.xml"
  },
  {
    "fileSize": "4096 bytes",
    "fileType": "Directory",
    "filename": "configuration"
  }
  {
    "fileSize": "0 bytes",
    "fileType": "Directory",
    "filename": "readme"
  }
]
``` 
