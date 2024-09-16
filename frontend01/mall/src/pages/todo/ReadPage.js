import React from 'react';
import { useParams } from 'react-router-dom';

function ReadPage(props) {
  
  const {tno} = useParams()
  
  return (
    <div className='text-3xl'>
      Read Page
    </div>
  );
}

export default ReadPage;