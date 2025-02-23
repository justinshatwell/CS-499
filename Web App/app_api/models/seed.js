const Mongoose = require('./db');
const Event = require('./nemaEvent');

// Read seed data from json file
var fs = require('fs');
var events = JSON.parse(fs.readFileSync('./data/events.json', 'utf8'));

// Delete any existing records, then insert seed data
const seedDB = async () => {
    await Event.deleteMany({});
    await Event.insertMany(trips);
    
};

//Close the MongoDB connection and exit
seedDB().then(async () => {
    await Mongoose.connection.close();
    process.exit(0);
});