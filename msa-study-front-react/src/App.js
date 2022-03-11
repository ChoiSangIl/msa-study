import { Button, Navbar, Container, Form, Row, Col, Carousel } from 'react-bootstrap';
import axios from 'axios'

function App() {
  return (
    <div>
      <Navbar>
        <Container>
          <Navbar.Brand href="#home"><img src="https://www.11st.co.kr/img/svg/logo/11st.svg"></img></Navbar.Brand>  
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
      <Container>

      <Carousel className="h-100">
        <Carousel.Item interval={1000}>
          <img
            className="d-block"
            height={400}
            src="//cdn.011st.com/11dims/resize/1240x400/quality/99/11src/browsing/banner/2022/03/04/33033/202203040109045660_12076829_1.jpg"
            alt="First slide"
          />
          <Carousel.Caption>
            <h3>First slide label</h3>
            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item interval={500}>
          <img
            className="d-block"
            height={400}
            src="//cdn.011st.com/11dims/resize/1240x400/quality/99/11src/http://cdn.011st.com/ds/2022/03/02/1415/2e88f3e4cab6ca4a64718ae8eab0a2ee.jpg"
            alt="Second slide"
          />
          <Carousel.Caption>
            <h3>Second slide label</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block"
            height={400}
            src="//cdn.011st.com/11dims/resize/1240x400/quality/99/11src/browsing/banner/2022/03/11/33033/20220311101811547_12091154_1.jpg"
            alt="Third slide"
          />
          <Carousel.Caption>
            <h3>Third slide label</h3>
            <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
      </Container>
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
