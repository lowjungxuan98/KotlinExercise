const express = require("express");
const cors = require("cors");
const socket = require('socket.io');
const app = express();
const PORT = process.env.PORT || 3000;
// const realTime = require("./app/controllers/realtime.controller");

var corsOptions = {
    origin: "http://localhost:8081"
};

app.use(cors(corsOptions));

app.use(express.json());

app.use(express.urlencoded({ extended: true }));

app.get("/", (req, res) => {
    res.json({ message: "Welcome to bezkoder application." });
});

require("./app/routes/student.routes")(app);

const server = app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});

app.use(express.static('public'));
const io = socket(server);
// const { createOrder, readOrder } = require("./orderHandler")(io);
// const onConnection = (socket) => {
//     socket.on("order:create", createOrder);
//     socket.on("order:read", readOrder);
// }
const { findAll } = require("./app/controllers/realtime.controller")(io);
const onConnection = (socket) => {
    socket.on("student:findAll", findAll);
}
io.on("connection", onConnection);

findAll()
    .then(() => console.log('Waiting for database events...'))
    .catch(console.error);