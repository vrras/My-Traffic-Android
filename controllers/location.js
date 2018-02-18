var connection = require('../config/mysql-db');

// add query functions
function getAllData(req, res, next) {
    var queryData = 'SELECT * FROM (SELECT a.*, b.full_name, b.flag FROM location_log a INNER JOIN users b ON a.id_user=b.id_user GROUP BY a.id_user, insert_date ORDER BY insert_date DESC) t GROUP BY id_user';

    connection.query(queryData, function(err, rows, fields){
        if (!err && (rows.length > 0)) 
        { 
            res.json({
                data:rows,
                status:true,
                code:200,
                msg:'Data found.'
            });
        } else {
            res.json({
                data:rows,
                status:false,
                code:400,
                msg:'Data not found.'
            });
            console.log(err);
        }
    });
}

function getSingleData(req, res, next) {
    var id=req.body.id_user;
    queryData='SELECT * FROM location_log WHERE id_user=?';

    connection.query(queryData, id, function(err, rows, fields) {
        if (!err && (rows.length === 1)) 
        {
            res.json({
                data:rows,
                status:true,
                code:200,
                msg:'Data found.'
            });
        } else {
            res.json({
                data:rows,
                status:false,
                code:400,
                msg:'Data not found.'
            });
            console.log(err);
        }
    });
}

function createData(req, res, next) {
    var lat =  req.body.lat; 
    var lng =  req.body.lng; 
    var id_user =  req.body.id_user; 
    
    var data = {
        latitude: lat,
        longitude: lng,
        id_user: id_user
    }

    var queryData = 'INSERT INTO location SET ?';

    connection.query(queryData, data, function(err, rows, fields) {
        if (!err) 
        {
            res.json({
                status:true,
                code:201,
                msg:rows.affectedRows + ' record(s) inserted.'
            });
        } 
        else 
        {
            res.json({
                status:false,
                code:400,
                msg:'Data not inserted.'
            });
            console.log(err);
        }
    });
}

function createDataLog(req, res, next) {
    var lat =  req.body.lat; 
    var lng =  req.body.lng; 
    var id_user =  req.body.id_user; 
    
    var data = {
        latitude: lat,
        longitude: lng,
        id_user: id_user
    }

    var queryData = 'INSERT INTO location_log SET ?';

    connection.query(queryData, data, function(err, row, fields) {
        if (!err) 
        {
            res.json({
                status:true,
                code:201,
                msg:row.affectedRows + ' record(s) inserted.'
            });
        } 
        else 
        {
            res.json({
                status:false,
                code:400,
                msg:'Data not inserted.'
            });
            console.log(err);
        }
    });
}

function updateData(req, res, next) {
    var id = parseInt(req.body.id_loc);
    var lat = req.body.lat;
    var lng = req.body.lng;
    var id_user = req.body.id_user;

    var data = {
        latitude: lat,
        longitude: lng,
        id_user: id_user
    }

    var queryData = 'UPDATE location SET ? WHERE id_loc = ?';
    
    connection.query(queryData, [data,id], function(err, rows, fields) {
        if (!err) 
        {
            res.json({
                status:true,
                code:201,
                msg:rows.affectedRows + ' record(s) updated.'
            });
        } 
        else 
        {
            res.json({
                status:false,
                code:400,
                msg:'Data not updated.'
            });
            console.log(err);
        }
    });
    
}

function removeData(req, res, next) {
    var id = parseInt(req.body.id_loc);

    var queryData = 'DELETE FROM location WHERE id_loc = ?';

    connection.query(queryData, [id], function(err, rows, fields) {
        if (!err) 
        {
            res.json({
                status:true,
                code:200,
                msg:rows.affectedRows + ' record(s) deleted.'
            });
        } 
        else 
        {
            res.json({
                status:false,
                code:400,
                msg:'Data not deleted.'
            });
            console.log(err);
        }
    });
}

module.exports = {
    getAllData: getAllData,
    getSingleData: getSingleData,
    createData: createData,
    createDataLog: createDataLog,
    updateData: updateData,
    removeData: removeData
};