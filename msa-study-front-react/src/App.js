import { Button, Navbar, Container, Form, Row, Col, Carousel } from 'react-bootstrap';
import axios from 'axios'

function App() {
  return (
    <div>
      <Navbar>
        <Container width="1012">
          <Navbar.Brand href="#home"><img src="https://image7.coupangcdn.com/image/coupang/common/logo_coupang_w350.png" width="174" height="41"></img></Navbar.Brand>  
          <Navbar.Collapse className="justify-content-center">
            <Form className="justify-content-center w-50 mt-4">
                <Form.Group className="mb-3" controlId="productSearch">
                  <Form.Control type="email" placeholder="상품명을 입력하세요" />
                </Form.Group>
            </Form>
          </Navbar.Collapse>

          <Navbar.Toggle />
          <Navbar.Collapse className="justify-content-end">
            <Navbar.Text>
              <a href="#login">Login...</a>
            </Navbar.Text>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Carousel className="h-100" w-100>
        <Carousel.Item interval={3000}>
          <img
            className="d-block w-100"
            height={450}
            src="https://static.coupangcdn.com/sa/cmg_paperboy/image/1646990498782/%5B%EC%88%98%EC%A0%95%5D220313_C1_%ED%99%94%EC%9D%B4%ED%8A%B8%EB%8D%B0%EC%9D%B4_SMD-16050_PC.jpg"
            alt="First slide"
          />
        </Carousel.Item>
        <Carousel.Item interval={3000}>
          <img
            className="d-block w-100"
            height={450}
            src="https://static.coupangcdn.com/wa/cmg_paperboy/image/1646983951284/220313_C1_PL_%EC%BF%A0%ED%8C%A1Only_SMD-16061_PC.jpg"
            alt="Second slide"
          />
        </Carousel.Item>
        <Carousel.Item interval={3000}>
          <img
            className="d-block w-100"
            height={450}
            src="https://static.coupangcdn.com/pa/cmg_paperboy/image/1646961363985/0313_PC_C1%281%29.jpg"
            alt="Third slide"
          />
        </Carousel.Item>
      </Carousel>
    </div>
  );
}

function doPay() {
  axios.post('/orders',{

  })
  .then(function (response) {
    // handle success
    console.log(response);
    alert(response.data);
  })
  .catch(function (error) {
    // handle error
    console.log(error);
    alert(error);
  })
  .then(function () {
    // always executed
  });
}

export default App;
