File Matching Spring Boot Application
This project is a Spring Boot application designed to efficiently match a reference text file, denoted as File A, against a pool of text files stored in a specified directory. 
The application reads the file paths of File A and the directory containing the other text files from its properties file.

**Objective
The primary objective of this application is to assign a score to each file in the pool based on its similarity to File A. This score serves to identify the file within the pool that best matches File A.
The similarity score is computed based on the comparison of words between the files being evaluated.

**Functionality
Upon execution, the application reads the content of File A and iterates through each file in the designated directory, calculating a similarity score for each.
The similarity score reflects the degree of resemblance between the content of each file and File A. This scoring mechanism enables efficient identification of the most relevant file in the pool.

**Technologies Used
Java
Spring Boot
Swagger
JWT
Docker


**Usage
To use this application:
Sign Up
Login To generate Token(That u use on any request)
Configure the paths of File A and the directory containing the pool of text files in the properties file.
Execute the Spring Boot application.
Review the generated scores to identify the text file that best matches File A.
Contribution
Contributions to this project are welcome. If you encounter any issues or have suggestions for enhancements, please feel free to submit a pull request or raise an issue on the GitHub repository.

**Notes:
-You will find needed environment variables on docker-compose file to use them on your yml
-You can use docker compose to run the project 

