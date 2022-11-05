Inside here force -> true with auto format the table
```
db.sequelize.sync({force:true})
  .then(() => {
    console.log("Synced db.");
  })
  .catch((err) => {
    console.log("Failed to sync db: " + err.message);
  });
```

auto generate the model
```
sequelize-auto -o "./auto" -d kotlin_exercise -h 127.0.0.1 -u root -p 3306 -x admin -e mysql
```