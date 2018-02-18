var express = require('express');
var router = express.Router();
//CONTROLLERS
var controller = require('../controllers/users');

router.post('/login', controller.doLogin);
router.put('/logout', controller.doLogout);
router.get('/', controller.getAllData);
router.post('/one', controller.getSingleData);
router.post('/', controller.createData);
router.put('/', controller.updateData);
router.delete('/', controller.removeData);

module.exports = router;
