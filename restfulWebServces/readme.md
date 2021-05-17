# RICHARDSON MATURITY MODEL

## LEVEL 0 
    Expose SOAP web services in REST style
    e.g:
        http://server/getPosts
        http://server/deletePosts
        http://server/doThis
       
## LEVEL 1
    Expose Resources with proper URI
    Proper use of HTTP methods not required
    
## LEVEL 2
    LEVEL 1 + Proper HTTP methods
    
## LEVEL 3 
    LEVEL 2 + HATEOAS
    Data + Next Possible Actions
    
# BEST PRACTISES for DESIGNING RESTFUL WEB SERVICES

## Consumer First
    1. Have a clear idea of who are your consumers 
    (is it a web application, is there going to be mobile app in future..)
    2. Name your Resources by keeping your consumers in mind (What do they think of the resources)
    3. Have a good documentation
    
## Make best use of HTTP
    1. use the appropriate HTTP method (GET, POST, PUT, DELETE) required for specific action.
    2. Ensure that you are sending proper Response Status back
        e.g: 
            200 - SUCCESS
            404 - RESOURCE NOT FOUND
            400 - BAD REQUEST
            201 - CREATED
            401 - UNAUTHORIZED
            500 - SERVER ERROR
            
## No secure info in URI
    Make sure no secure information is going in your URI
    
## Use Plurals
    e.g:
        Prefer /users to /user
        Prefer /users/1 to /user/1
        
## Use Nouns for Resources
    For exceptionsdefine a consistent approach
    e.g: PUT and DELETE for star on github gists
        PUT /gists/{id}/star
        DELETE /gists/{id}/star
    