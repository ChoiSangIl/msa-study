import { Button, Navbar, Container, Form, Row, Col, Carousel, InputGroup, DropdownButton, Dropdown, Alert, CloseButton  } from 'react-bootstrap';
import { Search, Person, Cart3, List } from 'react-bootstrap-icons';

import axios from 'axios'

const tdStyle = {paddingTop:"15px", paddingLeft:"15px", paddingRight:"15px"};

function App() {
  return (
    <div>
      <div style={{backgroundColor:"#f0f0f0",height:"32px"}}>
      </div>

      <Container style={{width:"1020px"}}>
        <table className="mt-2 mb-3">
          <colgroup>
            <col width="7%"/>
            <col width="10%"/>
            <col width="*"/>
            <col width="20%"></col>
          </colgroup>

          <tr>
            <td rowSpan={"2"} style={tdStyle}>
              <Button><List size={30}></List></Button>
            </td>
            <td style={tdStyle}><img src="https://image7.coupangcdn.com/image/coupang/common/logo_coupang_w350.png" width="174" height="41" ></img></td>
            <td style={tdStyle}>
              <InputGroup className="mb-1">
                  <DropdownButton
                    variant="outline-secondary"
                    title="전체"
                    id="input-group-dropdown-1"
                  >
                    <Dropdown.Item href="#">Action</Dropdown.Item>
                    <Dropdown.Item href="#">Another action</Dropdown.Item>
                    <Dropdown.Item href="#">Something else here</Dropdown.Item>
                    <Dropdown.Divider />
                    <Dropdown.Item href="#">Separated link</Dropdown.Item>
                  </DropdownButton>
                  <Form.Control type="search" placeholder="상품명을 입력하세요" />
                  <Button variant="outline-secondary">
                    <Search size={20}></Search>
                  </Button>
                </InputGroup>
            </td>
            <td style={tdStyle}>
              <Button variant="">
                <Person size={35}></Person>
                <br/><span style={{fontSize:"13px"}}>마이쿠팡</span>
              </Button>
              <Button variant="">
                <Cart3 size={35}></Cart3>
                <br/><span style={{fontSize:"13px"}}>장바구니</span>
              </Button>
            </td>
          </tr>

          <tr>
            <td style={{fontSize:"13px"}} colspan="2">
              <ul style={{listStyle:'none'}}>                  
                <li style={{float:'left', marginRight:"25px"}}>로켓배송</li>
                <li style={{float:'left', marginRight:"25px"}}>로켓프레시</li>
                <li style={{float:'left', marginRight:"25px"}}>쿠팡비즈</li>
                <li style={{float:'left', marginRight:"25px"}}>로켓직구</li>
                <li style={{float:'left', marginRight:"25px"}}>골드박스</li>
                <li style={{float:'left', marginRight:"25px"}}>이벤트/쿠폰 기획전</li>
                <li style={{float:'left', marginRight:"25px"}}>여행/티켓</li>
              </ul>
            </td>
            <td></td>
          </tr>
          
          <td></td>
        </table>
      </Container>

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
