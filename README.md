# Ironhack-final-project-backend
## How to run it:
1. Clone this reposutory
2. If you want to run it with live football results you have to change live-results-service properties:
	
	* set **api.football.api.key** on your own API-FOOTBALL key (https://rapidapi.com/api-sports/api/api-football/)
	
	* set **api.football.live** on **true**

3. Run config-server project
4. Run projects from this repo in the following order:
	
	1. discovery-server
	2. live-results-service
	3. user-info-service
	4. prediction-service
	5. live-results-service

5. Run front end project: https://github.com/patrykwieteska/Ironhack-final-project-frontend (http://localhost:4200)

### API Documentation (SWAGGER):
http://localhost:8400/predictmatch/api/v1/swagger-ui/#/

### Frontend part: https://github.com/patrykwieteska/Ironhack-final-project-frontend
### Remote config-server:https://github.com/patrykwieteska/Ironhack-final-project-config-server
### Remote config properties: https://github.com/patrykwieteska/Ironhack-final-project-cloud-config


## Documentation:


### 1. Use cases
1.1. Authorization
![image](https://user-images.githubusercontent.com/44143107/146634768-978b2114-5a43-4bf2-8b97-cfbd4c62afdf.png)




### 2. Class diagrams

1.1. Live-results-service
 ![image](https://user-images.githubusercontent.com/44143107/146634720-1c81e614-2314-475d-a4bc-bc4223716300.png)
 
1.2. User-info-service:
![image](https://user-images.githubusercontent.com/44143107/146634749-5be36bc6-3860-4844-b844-f61d7ca16717.png)


1.3. Prediction-service
![image](https://user-images.githubusercontent.com/44143107/146634741-01b5e6a8-2671-4ced-a3eb-70119dc4e643.png)


### 3. Architecture



  
  
