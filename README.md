# News_Portal
CONTRIBUTORS: Allan Mugweru.

This project is a website whose function is to create an API that create and displays an Organisation"s news, users and departments.

The website is operational and all its functionalities are working well. These are functions like creating a user,adding the user to the user list. Creating a department and adding the department to the departments list. Also creating news and posting the news on news list.

For installation Use git, GitHub, and for using the app you should use it on postman.

Creation of this project I used Intellij and Spark which helped in adding the markup language and functionality.

For more Contribution, Pull requests are welcomed. For major changes, please open an issue first to discuss what you would like to change. Please make sure to update tests as appropriate.

Website's link to view Live click here:https://github.com/Allantoizmughus/Organisational-new-portal-app.git

To run the app using postgres, the following commands;
### DATABASE NAME
`\c news_portal;`
`\c news_potal_test;`
### CREATE  TABLES
* `nativage to main/resources/db  `
* `run  psql < create.sql`
### DROP  TABLES
* `nativage to main/resources/db  `
* `run  psql < drop.sql`


## End points
| URL                                            | HTTP Verb   |                                 DESCRIPTION|
|--                                              |  ---        |                                   ---      |
|/user                                           |get          |     Get users                              |
|/users/:id                                      |get          |     get users using id                     |
|/department                                     |get          |     Get departments                        |
|/department/:id                                 |get          |     get department using id                |
|/departments/:id/user                           |get          |     get users in a specific department     |
|//department/:id/news                           |get          |     get news in a specific department      |
|/user/:id/departments                           |get          |     get users in a department              |
|/news                                           |get          |     Get news                               |
|/news/:id                                       |get          |     get news using id                      |   
|/user/new                                       |post         |     add a new user                         |
|/department/new                                 |post         |     add a new department                   |
|/news/new                                       |post         |     add news                               |
|/department/:departmentId/news/:newsId          |post         |     add news in department                 |
/department/:departmentId/users/:userId          |post         |     add user in department                 |                    |


MIT License

Copyright (c) 2021 Allan Mugweru.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.