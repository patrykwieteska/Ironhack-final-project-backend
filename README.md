<div style="text-align:center"><img width="50px" src="https://github.com/patrykwieteska/Ironhack-final-project-frontend/blob/main/src/assets/images/logo/logo_no_bg_black.png" /></div>
<!-- ![alt text](https://github.com/patrykwieteska/Ironhack-final-project-frontend/blob/main/src/assets/images/logo/logo_no_bg_black.png) -->
  

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

1.2. User info

![image](https://user-images.githubusercontent.com/44143107/146634802-af6b02b3-0980-4fca-99b0-3a853815d52e.png)

1.3. Live results

![image](https://user-images.githubusercontent.com/44143107/146634810-fc12619a-e44e-4edd-81b7-9f60e801e3e0.png)

1.4. Predictions

![image](https://user-images.githubusercontent.com/44143107/146634818-7634c21f-aeee-4d78-9a6f-2a61767197af.png)


### 2. Class diagrams

2.1. Live-results-service

 ![image](https://user-images.githubusercontent.com/44143107/146634720-1c81e614-2314-475d-a4bc-bc4223716300.png)
 
2.2. User-info-service:

![image](https://user-images.githubusercontent.com/44143107/146634749-5be36bc6-3860-4844-b844-f61d7ca16717.png)


2.3. Prediction-service

![image](https://user-images.githubusercontent.com/44143107/146634741-01b5e6a8-2671-4ced-a3eb-70119dc4e643.png)


### 3. Architecture

![image](https://user-images.githubusercontent.com/44143107/146634905-eca2e0a1-e24d-4671-b4d8-67d3d3a42329.png)


  
