## Contributing
When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.


## Prerequisites 
* JDK 22: This project uses JDK 22, which means this should be installed on your device.
* Apache Maven: This is a Maven project, so this should also be installed.
* MySQL: The project uses MySQL for the database, so this should be installed on your device.
* MySQL workbench: This is where the database is set, so this should be installed and properly set up too. 
* IntelliJ: We have used intelliJ as IDE, but any other like VSCode could also work. 
* Discord: We communicate via discord, so install this to get in contact with us. 

## Setup
* Clone the repository
* Use git pull
* Look through the code and how it has been set up.
* Setup your own local database in the MySQL workbench. 
* Run the program locally with port 8080 - use all the functions the program has to offer, to ensure everything works. 

## Pull request process
Ensure any install or build dependencies are removed before the end of the layer when doing a build.
Update the README.md with details of changes to the interface, this includes new environment variables, exposed ports, useful file locations and container parameters.
Increase the version numbers in any examples files and the README.md to the new version that this Pull Request would represent. The versioning scheme we use is SemVer.
You may merge the Pull Request in once you have the sign-off of two other developers, or if you do not have permission to do that, you may request the second reviewer to merge it for you.

## Workflow 
* Create a new feature-branch with a relevant name for the feature your are implementing.
* Make the code and commit the changes to your branch. 
* After completing the feature, push the branch to the git repository. 
* Our CI workflow will then attempt to automatically rebase and build the branch with Maven. If no errors are found (excluding tests), the feature-branch will be able to automatically merge with the main branch. 
* When this has been done, all team members should pull the latest changes, to ensure everyone is up to date. 

## Package structure
**Under java we have:**
* Controller
* Model
* Repository
* Service
* ProjectPortalApplication
  * main

**Under resources we have:**
* static
  * css
  * logos 
* templates
  * fragments
    * footer
  * most html  

## How the structure works
We have controller, model, repository and service for all our classes, which are:
* Company
* User
* Project
* Subproject
* Task

In the resources folder we have everything related to the design of the program, which is the html-files, the css-files, the javascripts and logos. 

## Code style guide
## Java
* The name of the function is what the function does. 
* All code is in english
* Respect the package structure - make controllers in the controller folder and so on.
* Use proper spaces throughout the code, for example leave space between methods. 
* Use comments for each method, so that another or future team member can understand what the method does. 
## MySQL
* We have two files for our SQL - create_projectplanner.sql and insert_projectplanner.sql
* The create is to set up the database, whereas insert is to insert new data. 
* Write chronologically for the different classes:
  * Company (at the top)
  * User
  * Project
  * Subproject
  * Task (at the bottom)
## HTML
* We aim for making CRUD for all classes, but have later in our process figured out that some are unnecessary or can be in another file. If in doubt, discuss with team. 
* Leave proper spaces so the code is easy and nice to read.
* We use div since we in our CSS use one file to control the design of the html. 
## CSS
* We use, if possible, one file to control the design of the code. 
* If an html-file needs different keywords, then discuss with team and create a new css file for this. 