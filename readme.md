### Technical exercice - Backend

This application is able to convert some JSON input from the Laravel Validators notation to a structured tree in JSON.

#### How to deploy

```
git clone https://github.com/noefleury-infomaniak/rh-trainees.git ; cd rh-trainees
docker-compose -f deployment/docker-compose.yml up -d
```

This will :

- run the Flask server in a Docker container
- be accessible through the port 5001 from the host

#### How to test

We can use Postman to test the implementation.

Here is the link to use : https://rh-trainees.crust.ac/expand_validator

A JSON need to be sent as the body of this POST request.

As an example :

```json
{
	"a.*.b": "string",
	"x.y.z": "integer|max:23",
}
```

The response will give a JSON with a status (good or not). If that's ok, the structured JSON will be here too, it not the error will be seen.

#### Important informations

You'll cannot access the web server if you are using the VPN of our work.

That's because we block all packets between our VPN and our unmanaged clouds.

So just disable this VPN if you are in home office.