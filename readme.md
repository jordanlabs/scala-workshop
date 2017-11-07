# Scala Workshop

## Endpoints

```
GET    /health              # Returns "ok"
GET    /numbers             # Returns a list of numbers
GET    /numbers/sum         # Returns the sum of the numbers in the list
GET    /numbers/3/fizzbuzz  # Returns the 'fizzbuzz' value of the number
GET    /numbers/3           # Returns a specific number
POST   /numbers             # Adds a new number to the list of numbers
DELETE /numbers/3           # Deletes the specified number
```

Check `http://unfiltered.ws/06/d.html` for helpful curl commands.

TODO: Use argonaut-unfiltered integration

### Project Template Setup

Initial template was set up by installing conscript `cs` and giter8 `g8` commands.

```sh
curl https://raw.githubusercontent.com/foundweekends/conscript/master/setup.sh | sh
source ~/.bashrc
 
cs foundweekends/giter8

sbt new unfiltered/unfiltered-netty.g8 --name rental-api
```
