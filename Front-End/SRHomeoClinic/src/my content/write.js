import React, { useState } from 'react'
import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css';
const Write = () => {
    const [value,setValue]=useState('');
    
  return (
    <div className='writing content'>
        <div className="material">
            <input type="text" placeholder='title' />
            <div className="editor-container">
                <ReactQuill className='editor' theme='snow' value={value} onChange={setValue} />
            </div>
        </div>
        <div className="menu">
            <div className="item">
                <h1>Publish</h1>
                <span>
                    <b>Status: </b>Draft
                </span>
                <span>
                    <b>Visibility: </b>Public
                </span>
                <input style={{display:'none'}} type="file" id='file' name='' />
                <label htmlFor="file">Upload image</label>
                <div className="buttons">
                    <button  id='draft' >Save draft</button>
                    <button className='login-btn'>Update</button>
                </div>
            </div>
            {/* <div className="item">i2</div> */}
        </div>
    </div>
  )
}

export default Write
