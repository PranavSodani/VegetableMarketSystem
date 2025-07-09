Project Explanation = 
* Index.jsp - 
  - This is the main landing page of my website. 
  - {pageContext.request.contextPath} - This helps in fetching the base url.

* Login.jsp and LoginServlet.java -
  - This is the login page .
  - in the servlet section when a user logs in then with the help of a http session i am setting a token called isLogged.
  - then i am redirecting to the 

* Navbar.jsp - 
  - In this it is present in the component folder and its called everyhwere. 
  - in this dynamically url is setted in product button as per user type.

* LogoutServlet.java - 
  - In this i am logging out the user completely by invalidatin the session and then redirecting to index.jsp 
* Product.jsp - 
  - In this first of all the synonym and the names of product are fetched as list and map via attributes set in the session.
  - Then its showing in the form of card. 
* script (fetchingQuantiyAndUnit.js) - 
  - This script is immediatley getting fired when server runs and its getting the number of units of product which helps in the calculatin of price. 

*** search functionality - 
  - In this first DOMContentLoaded , it ensure that the html content is loaded completely.
  -  Levenshtein Distance Function - in this via dp i am checking that how many insertion or deletions are required to match string present in the searchbar.
     if its greater then a cerating threshold(3) then only i am showing it . 
     for this logic i have used 2d dp.

* cart functionality - 
  - Custom confirmation popup to check user to delte some item from cart is implemented via bootstrap class "confirm-popup".
  - Notification Badge - a badge attribute is presetn in the session whihc is updated whenever some item is added or deleted from cart and then showd on the button of cart.
  - show summary section - simple calcutation is done in this part. 

** Profile.jsp and UpdateProfileServlet.java -
  - i am storing the image as image address in the table.
  - *****(File upload) 
  - we use part datatype. it stores the metadata of the uploaded file.
  - When a user uploads a file (like a profile image) through a form, the server receives the file as a Part object.
    The Part object holds the actual file data (the image itself) and some information about the file (like its original name).
  - You can use methods on Part to:
  - Save the file to a folder on the server using part.write("path").
  - Get the file name using a helper method.
 

	
