var connection = require('../config/mysql-db');

// add query functions
function doLogin(req, res, next) {
    var user = req.body.username;
    var pass = req.body.password;

    var queryData = 'SELECT * FROM users WHERE username=? AND password=md5(?)';

    connection.query(queryData, [user,pass], function(err, rows, fields){
        if (!err && (rows.length > 0)) 
        { 
            res.json({
                data:rows,
                status:true,
                code:200,
                msg:'Login success.'
            });

            var queryData = 'UPDATE users SET flag=1 WHERE username = ?';
    
            connection.query(queryData, [user], function(err, rows, fields) {
                if (!err) 
                {
                    console.log("Login!");
                } 
                else 
                {
                    console.log(err);
                }
            });

        } else {
            res.json({
                data:rows,
                status:false,
                code:400,
                msg:'Login failed.'
            });
            console.log(err);
        }
    });
}

function doLogout(req, res, next) {
    var id = parseInt(req.body.id_user);

    var queryData = 'UPDATE users SET flag=0 WHERE id_user = ?';
    
    connection.query(queryData, [id], function(err, rows, fields) {
        if (!err) 
        {
            res.json({
                status:true,
                code:201,
                msg:'Logged out.'
            });
        } 
        else 
        {
            res.json({
                status:false,
                code:400,
                msg:'Failed to logout.'
            });
            console.log(err);
        }
    });
    
}

function getAllData(req, res, next) {
    var queryData = 'SELECT * FROM users';

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
    queryData='SELECT * FROM users WHERE id_user=?';

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
    var name =  req.body.full_name; 
    var user =  req.body.username; 
    var pass =  md5(req.body.password); 
    
    var data = {
        full_name: name,
        username: user,
        password: pass
    }

    var queryData = 'INSERT INTO users SET ?';

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

function updateData(req, res, next) {
    var id = parseInt(req.body.id_user);
    var name =  req.body.full_name; 
    var user =  req.body.username; 
    var pass =  md5(req.body.password);

    var data = {
        full_name: name,
        username: user,
        password: pass
    }

    var queryData = 'UPDATE users SET ? WHERE id_user = ?';
    
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
    var id = parseInt(req.body.id_user);

    var queryData = 'DELETE FROM users WHERE id_user = ?';

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
    doLogin: doLogin,
    doLogout: doLogout,
    getAllData: getAllData,
    getSingleData: getSingleData,
    createData: createData,
    updateData: updateData,
    removeData: removeData
};