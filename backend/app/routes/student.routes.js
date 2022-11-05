module.exports = app => {
  const student = require("../controllers/student.controller");

  var router = require("express").Router();

  router.post("/", student.create);
  router.get("/", student.findAll);
  router.get("/:roll_no", student.findOne);
  router.put("/:roll_no", student.update);
  router.delete("/:id", student.delete);
  router.delete("/", student.deleteAll);

  return app.use('/api/student', router);
};