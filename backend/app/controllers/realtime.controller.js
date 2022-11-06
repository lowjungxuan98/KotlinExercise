const MySQLEvents = require('@rodrigogs/mysql-events');
const { Socket } = require('socket.io');
const { sequelize, DataTypes, instance } = require('../config');
const Student = require('../../auto/student_tables')(sequelize, DataTypes)

module.exports = (io, socket) => {
    const realtime = async function () {
        await instance.start();
        instance.addTrigger({
            name: 'monitoring my_first_db ...',
            expression: 'kotlin_exercise.*',
            statement: MySQLEvents.STATEMENTS.ALL,
            onEvent: (event) => {
                console.log("Waiting data incoming...")
                Student.findAll({ raw: true })
                    .then(data => {
                        io.emit('data', data);
                        console.log(data);
                    })
                    .catch(err => {
                        res.status(500).send({
                            message:
                                err.message || "Some error occurred while retrieving tutorials."
                        });
                    });
            }
        })
        instance.on(MySQLEvents.EVENTS.CONNECTION_ERROR, console.error);
        instance.on(MySQLEvents.EVENTS.ZONGJI_ERROR, console.error);
    }
    return {
        realtime
    }
}