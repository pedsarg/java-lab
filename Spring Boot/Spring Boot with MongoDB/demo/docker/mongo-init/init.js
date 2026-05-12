db = db.getSiblingDB('workshop_mongo');

db.createCollection('user');

db.user.insertMany([
  {
    name: 'Alfred',
    email: 'Alfred@email.com'
  },
  {
    name: 'Debi',
    email: 'deb@email.com'
  }
]);