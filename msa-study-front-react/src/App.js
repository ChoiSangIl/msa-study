import Button from 'react-bootstrap/Button';
import axios from 'axios'

function App() {
  return (
    <div class="container-fluid mt-3">
      <Button variant="primary" onClick={doPay}>주문</Button>
    </div>
  );
}

function doPay() {
  axios.get('/order')
  .then(function (response) {
    // handle success
    console.log(response);
    alert(response.data);
  })
  .catch(function (error) {
    // handle error
    console.log(error);
  })
  .then(function () {
    // always executed
  });
}



export default App;
