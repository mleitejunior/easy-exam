import express from 'express';
import routes from './routes';

const app = express();

app.get('/', (request, response) => response.json({ message: 'ola' }));

app.listen(3333, () => {
  console.log('🙋 Server started on port 3333');
});
