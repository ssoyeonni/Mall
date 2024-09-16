import { RouterProvider } from "react-router-dom";
import root from "./router/root"

function App() {
  return (
    // 이 app.js로 들어오면 바로 router 재생시킴
    <RouterProvider router={root}/>
  );
}

export default App;
