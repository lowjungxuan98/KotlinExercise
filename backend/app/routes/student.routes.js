module.exports = app => {
    const student = require("../controller/student.controller");

    var router = require("express").Router();

    // Create a new Tutorial
    router.post("/", student.create);

    // Retrieve all Tutorials
    router.get("/", student.findAll);

    // Retrieve a single Tutorial with id
    router.get("/:roll_no", student.findOne);

    // Update a Tutorial with id
    router.put("/:roll_no", student.update);

    // Delete a Tutorial with id
    router.delete("/:id", student.delete);

    // Delete all Tutorials
    router.delete("/", student.deleteAll);

    return app.use('/api/student', router);
};