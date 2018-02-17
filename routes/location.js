var express = require('express');
var router = express.Router();
//CONTROLLERS
var controller = require('../controllers/location');

router.get('/', controller.getAllData);
router.post('/one', controller.getSingleData);
router.post('/', controller.createData);
router.post('/log', controller.createDataLog);
router.put('/', controller.updateData);
router.delete('/', controller.removeData);

module.exports = router;
