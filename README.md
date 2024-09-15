# Pizzeria (2022)

## User Management
Users can register if they do not already have an account. After providing the required information, they can log in to view and edit their personal details, as well as review their previous orders.

## Admin
An admin users (email: admin | password: admin) can view all registered users and their data. The admin can reset passwords to default, delete users and their data from the system, and notify affected users via email. The admin also has access to statistical data, such as the largest orders ever placed, the most ordered product, and the least ordered product. Additionally, the admin can manage store products by adding new ones, deleting existing ones, and editing product details, including name, price, description, and image.

## About the Project
This Spring-based application allows users to view the pizzeria's products. Logged-in users can add any number of products to their cart, modify the cart (increase or decrease quantities, delete products), and place orders. After placing an orders, users receive an email notification indicating that their orders is being processed.

### Additional Information
The application was developed using the Spring framework in Java, with Maven and Thymeleaf, and utilizes a MySQL database. It can be run in a Docker environment (requiring a MySQL image). After executing the command docker-compose up --build, the application will be accessible at localhost:8090.

The application includes CRUD operations with appropriate error handling and uses SQL queries alongside JDBC for database interactions.

