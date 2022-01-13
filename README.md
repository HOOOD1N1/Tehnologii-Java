# Tehnologii-Java
## Lab1 - I solved the first point in the laboratory exercise. I added a form inside the index.html page, while the servlet is created inside the FirstServlet class. The sorting of the file called repository.txt is done inside the class FileSort

...

## Lab7-8 -  I solved all tasks from lab7, expect the task regardind @Observes, and for lab8 I solved the first task, creating a web service

## Lab9 - I solved the first task, securing the app

## Lab10 - I solved the last 3 tasks

# Fake News project

Written using Spring Boot, this project uses Twitter API, Hibernate, Rest services, Dependency Injection, and AOP.

The app provides services to login and signup users, to check whether a given tweet is fake news, containing unreliable information, to return a list of all tweet checks made by a user, and other smaller services.

The most important classes are CheckController, AlgorithmService, CheckService, UserController, TweetAccountService, TweetService, UserService, Credentials, and TweetController. The app also contains 4 entities corresponding with the tables in the database, and 3 classes for aspect purposes.

## Inside CheckController, which is a RestController, we inject using @Autowired the check and user services. The most important methods of this class are tweetCheck and getHistory. The first one receives 2 path variables, tweetId and userId, and a url inside the request body, and returns a JSON object, calling checkService.tweetCheck with these 3 variables.

The second one receives one path variable called userId and returns a JSON object containing a list of tweet checks made by the user with the given userId, calling checkService.checkHistory with this variable.


## Inside AlgorithmService, we inject using @Autowired the check service. The main method of this class is algorithmRun, which receives the parameters (Status status, UsersResources user, Long userId, String url). We get the tweet's account, text, retweets count, like count, followers count and if the account is verified or not, and we construct the algorithm. 

## The algorithm is as follows:
1.We check the likes count. If it smaller than 1/20 of the followers count, we subtract 10 points from the score and we add in an array called reasons the reason why we subtracted the points

2.We check the retweets count. If it smaller than 1/1000 of the followers count, we subtract 10 points from the score and we add in an array called reasons the reason why we subtracted the points

3.We check if the text contains extreme word such as BREAKING, and if it contains them, we subtract 10 points.


4.We check the date of the creation of the account. If it is created less than a year ago or less, we subtract more and more points and we add in the array the reason why we subtracted the points

5. We check if the account is verified. If it is, we add 10 points, because Twitter vouches for the veridicity of their words, otherwise we subtract 10 points.

In the end we add this check to the database. 


## Inside CheckService, the method tweetCheck gets the instance of TwitterFactory and we get the all the info for the tweet and the account , and we run the algorithm to get the score.

## Inside UserController, the login adn signup methods receive in the request body a username, a password and an email, and using hibernate they check to see if the user is logged in, or if the user doesn't exist, they create a new user in the database, and return a JSON object with some user data and a key value pair called login that is true if there is no error, or false if there is.

## Inside TweetService, the class is injected a TweetRepository object, and all the methods manage selecting,saving, updating and deleting tweets from the table Tweet using Hibernate. The TweetController's methods all use methods from this class


## Inside UserService, we can find the the bussiness logic for login and signup, and other JPA bussiness methods.

## The Credentials class is a template for the JSON objects returned to the client containing info about the user.





