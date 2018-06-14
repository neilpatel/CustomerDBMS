# CustomerDBMS
The purpose of this personal project was to assist users view, search and sort customer data provided from a CSV file. 
___
## Necessary Files
+ Main Source File
	+ CustomerDBMS
+ Additional Files
	+ Address
	+ City
	+ Company
	+ County
	+ Customer
	+ Email
	+ ExportHTML
	+ Fax
	+ FileParser
	+ FirstName
	+ LastName
	+ MouseEvents
	+ Phone
	+ SortOrder
	+ State
	+ UserInterface
	+ Web
	+ Zip

___
## Usage
+ Clone the repository
	+ `git clone https://github.com/neilpatel/CustomerDBMS.git`
+ To Compile:
	+ `javac *.java`
+ To Execute:
	+ `java CustomerDBMS`
+ Screenshot Provided below
	+ ![Compile-Execution Screenshot](https://github.com/neilpatel/CustomerDBMS/blob/master/screenshots/Compilation-Execution.PNG)
___
## Customer Profile Database Features
+ File
	+ Open
		+ Open a .CSV file
	+ Generate HTML Directory
		+ Create a HTML webpage with the CSV file currently loaded
			+ Depending on the size of the CSV, this process may take some time
		+ You will be prompted with a success or failure message upon requesting to generate HTML directory
		+ *You must have a CSV file loaded in order to generate a HTML webpage*
	+ Clear
		+ Clear the contents of the loaded file
	+ Exit
		+ Exit the application
	+ Screenshot provided below
		+ ![FileMenu Screenshot](https://github.com/neilpatel/CustomerDBMS/blob/master/screenshots/FileMenu.PNG)
___
+ Help
	+ A dialogue box with developer's information 
	+ Screenshot provided below
		+ ![HelpMenu Screenshot](https://github.com/neilpatel/CustomerDBMS/blob/master/screenshots/HelpMenu.PNG)
___
<p align = "center"> <b> Hope you like the project!!  </b> </p>

___
## Additional Hints:
+ Make sure you are using a CSV file. A Excel formatted CSV file may not be compatible.
	+ A good way to check is to open the directory with a text editor and make sure the fields are surrounded with quotes.