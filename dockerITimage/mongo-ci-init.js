// Create database user for application
db.createUser({ user: 'dev', pwd: 'password', roles: [ { role: 'readWrite', db: 'moviedb' } ] })
// Connect to database with this new user
db.auth('dev', 'password')
// Create collections
db.createCollection('movie')