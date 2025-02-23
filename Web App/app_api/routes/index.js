const express = require('express');
const router = express.Router();

const eventsController = require('../controllers/events');

router
    .route('/events')
    .get(eventsController.eventList)
    .post(eventsController.eventsAddEvent);

module.exports = router;