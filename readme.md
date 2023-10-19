Frontend
1. Create vue project
	- `npm install vue/cli`
	- `vue create $project_name`
2. Copy all the files under front folder to your vue project
3. Install all the dependencies
	- `npm i`
4. Run the frontend vue project
	- `npm run serve `

Backend
1. Open the backend folder in the intellij IDEA
2. Configure the database connection:
	In `application.yaml`  - revise the username, password, database name to your own
3. Build and run application

Database
1. create a database named student_system;
2. import the student.sql and user.sql, two tables (student and user) will be created
	`mysql -u username -p student_system < student.sql`
	`mysql -u username -p student_system < user.sql`
