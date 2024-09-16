// 라우터 관련 설정

import { Suspense, lazy } from "react"; // 원하는 페이지만 새로고침(?), 지엽 로딩
import todoRouter from "./todoRouter";

const { createBrowserRouter } = require("react-router-dom");

const Loading = <div>Loading...</div>
const Main = lazy(() => import("../pages/MainPage"))

const About = lazy(() => import("../pages/AboutPage"))

const TodoIndex = lazy(() => import("../pages/todo/IndexPage"))



const root = createBrowserRouter ([
  {
    path: '', // 기본화면(/)일 때
    element: <Suspense fallback={Loading}><Main/></Suspense>  // element: 어떤 컴포넌트 보여줄 것인지
  },
  {
    path: 'about',  // 이 경로로 들어가면
    element: <Suspense fallback={Loading}><About/></Suspense> // 위에 설정한 About에 대한 페이지 보여줌
  }
  ,
  {
    path: 'todo', 
    element: <Suspense fallback={Loading}><TodoIndex/></Suspense>,
    children: todoRouter()
  }
])

export default root;