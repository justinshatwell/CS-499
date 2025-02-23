const mongoose = require('mongoose');
const Event = require('../models/nemaEvent')
const Model = mongoose.model('events');

//Get all events

const eventList = async(req,res) => {
    const q = await Model
            .find({})
            .exec();


    if(!q) {
        return res
                .status(404)
                .json(err);
    }
    else {
        return res
                .status(200)
                .json(q);
    }
};

const eventsAddEvent = async(req, res) => {
    const newEvent = new Event({
        name: req.body.name,
        month: req.body.month,
        day: req.body.day,
        year: req.body.year,
        hour: req.body.hour,
        minute: req.body.minute,
        description: req.body.description
    });

    const q = await newEvent.save();

        if(!q)
        {
            return res
                .status(400)
                .json(err);
        }
        else
        {
            return res
                .status(201)
                .json(q);
        }
}

module.exports = {
    eventList,
    eventsAddEvent
};