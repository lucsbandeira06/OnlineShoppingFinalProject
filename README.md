# OnlineShoppingFinalProject #
## Student name: Lucas Bandeira ##
## Student Identification: 23884 ##



### Requirements: ###

1.Authentication
- [x] Allow User to Signup 
- [x] Log In using username and password
- [x] Store userID and token once logged in, to keep the user logged in (even after restarting the app)

2.Product Listing
- [] List Product Categories
- [x] On clicking a Category, list Products in that Category
- [x] On clicking a Product, show Product description, show buy button and controls to change quantity.

3.Cart
- [x] Show cart summary
- [x] Show total amount
- [] Purchase button to place an order, show order notification

4.Show order history
- [] List users orders
- [] On clicking an Order, show Order details and Products ordered
- [] On clicking a Product, take them to Product description page created for 3.3

5.Show User details
- [x] Use the stored userID to show user details
- [x] Show a random circular profile image from https://thispersondoesnotexist.com/
- [x] Show Logout button, on click take back to Signup / Log In page (Restart should not auto login after logout)

6.UI/Implementational Requirements
- [x] RecyclerView used for all Lists: Categories, Products, Orders
- [x] If logged in, attach authentication token to all requests until logout
- [] Add a small "About this app" button in the profile page, that shows a page on click with your copyright details and credits

7.Bonus
- [x] ViewPager2 with bottom TabLayout for: Shop, Cart, Orders, Profile icons
- [] Show a map marker based on the GPS co-ordinates in user profile (Hint: Follow instructions given here)

JSON API Links:
Root URL: https://console.firebase.google.com

- [x] User Signup: POST /users
- [x] User Sign In: POST /auth/login
- [x] Product Categories: GET /products/categories
- [] Products in a particular category: GET /products/categories/electronics
- [x] Purchase a cart: POST /carts
- [] Retrieve order history for user id 2: GET /carts/user/2


## Report ##

On this assigment I had to create an online shopping mobile application using kotlin as well as storing all the data into a cloud database (API).
I decided to use Firebase instead of fakestoreAPI on this project based on the material I used for research to create this application.

After I decided which dependencies I was going to use on my project, I had to add them to the graddle:app as well as to the graddle at console level. Once I had done that and tested if my app was connected to its cloud database, I started by creating the login acitivity and the register acitivity. I used a very basic design with username and password authentication for the login screen. Once I had a login activity, next step was to test if the authentication would work properly.

In light of the above, I created a class called User where I would store the details of the users created on the online shopping app containing variables such as: first name, last name, email, password. The register activity was created then to generate a user collection and then once the user logged in the application, he would go to the main activity.

To make it easier to test the functions I had created so far, I created a base activity which would show the progress dialog as well as display errors while trying to run the application. Moreover, I created a function to only exit the application once you pressed the back button twice instead of the default mode.

Next step was to create a user details activity to display the information we just stored after registering a user. The information displayed on this activity was retrieved from the users collection in the authentication environment on Firebase. To display it on the actual application, I had to create a function to first send this information to the database and then another function to retrieve the same information which would be displayed if the unique id on the cloud database matches the id used to log in the app.

So far I had created the login credentials and a activity to display it, therefore the next step was to create a navigation dashboard on the bottom of the application, so the user would be able to navigate between activities smoothlier. This dashboard is composed of 3 fragments: Dashboard, products and orders. On the dashboard fragment(homescreen) I added to the toolbar a icon:button to access the settings and the cart.

On the settings activity you can see the user information as well as edit it. On top of that, there is a field to add an address that is also going to be stored on the database. That is important because we need an address to buy online, otherwise we would not be able to receive the products we buy on the internet.

To add a product to the cart, we first need to have a products list on our application. I created a recycler view to display the details of the products added on the product activity. Also, I had to create a new collection of products on firebase which was achieved following a similar procress to create the user collection. The same applies to upload and retrieve data from firebase. This products can only be seen by the user id who added them to the app as well. If you log in with a different user, you will not be able to see any products on the product list as you have not added any to the app yet.

Back on the dashboard activity, I created a list of products as well, but this time all the products uploaded by every user will be displayed on this screen. I added a set on click listener to every product which would take the user to a product detail screen where the user is able to add a product to his cart.

On the cart activity, the user can add or remove an item depending on the stock availability of that specific product and he can also go to the checkout activity once he decides which products he is buying and its quantity.

Unfortunately, I was not able to completely finish this application, however I can honestly say it was the most enjoyable and challenging project I have ever done since I started studying computer science. I hope to finish this project in the near future.

## Youtube link: ##
https://www.youtube.com/watch?v=sB26HwZfsRA

