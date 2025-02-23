const mongoose = require('mongoose');

const eventSchema = new mongoose.Schema({
    name: {type: String, required: true},
    month: {type: Number, required: true },
    day: {type: Number, required: true},
    year: {type:Number, required: true},
    hour: {type: Number, required: true},
    minute: {type: Number, required: true},
    description: {type: String, required: true}

});
const Event = mongoose.model('events', eventSchema);
module.exports = Event;