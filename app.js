//Slip 5: Using nodejs create a web page to read two file names from user and append 
//contents of first file into second file . 
const express = require('express');
const fs = require('fs');
const path = require('path');

const app = express();
const port = 3000;

app.use(express.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

app.post('/appendFiles', (req, res) => {
  const firstFileName = req.body.firstFileName;
  const secondFileName = req.body.secondFileName;

  // Read contents of the first file
  fs.readFile(firstFileName, 'utf8', (err, data) => {
    if (err) {
      return res.send(`Error reading ${firstFileName}: ${err.message}`);
    }

    // Append contents to the second file
    fs.appendFile(secondFileName, data, (err) => {
      if (err) {
        return res.send(`Error appending to ${secondFileName}: ${err.message}`);
      }

      res.send('Files appended successfully!');
    });
  });
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
