# Ironhack-final-project-backend
## How to run it:
1. Clone this reposutory
2. Clone config-server repository: https://github.com/patrykwieteska/Ironhack-final-project-config-server
3. If you want to run it with live football results you have to change live-results-service properties:
	
	* set **api.football.api.key** on your own API-FOOTBALL key (https://rapidapi.com/api-sports/api/api-football/)
	
	* set **api.football.live** on **true**

4. Run config-server project
5. Run projects from this repo in the following order:
	
	1. discovery-server
	2. live-results-service
	3. user-info-service
	4. prediction-service
	5. live-results-service

## API DOCUMENTATION (SWAGGER):
http://localhost:8400/predictmatch/api/v1/swagger-ui/#/

  
  
