//Connection
var mysql = require('mysql');

var connection = mysql.createConnection({
    host     : '127.0.0.1',
    user     : 'root',
    password : '',
    port     : 3306,
    database : 'delivery_db',
    timezone : '+07.00' 
  });

  connection.connect(function(err) {
    if (err) throw err;
    console.log("Database Connected!");
  });

module.exports = connection;
