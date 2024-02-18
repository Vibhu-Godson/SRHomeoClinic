import React from 'react'
import {Link} from 'react-router-dom'
import Sider from './sider';
const single = () => {
  return (
    <div className='single content'>
        <div className="description">
            <img src="https://th.bing.com/th/id/OIP.bpJTixcJ9eRwEFjKsApJ8QHaEo?rs=1&pid=ImgDetMain" alt="" />
            <div className="user">
                <img src="https://th.bing.com/th/id/OIP.vgT-xN93AGkhrGpIEaz-sgHaJG?w=194&h=239&c=7&r=0&o=5&dpr=1.5&pid=1.7" alt="" />
                <div className="info">
                    <span>John</span>
                    <p>posted 2 days ago</p>
                </div>
                <div className="edit">
                    <Link to="/write?edit=2">
                    <img src="edit.jpeg" alt="" />
                    </Link>
                    <img src="delete.jpg" alt="" />
                </div>
            </div>
            <h1>Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint, exercitationem!</h1>
            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum non esse nisi sint. Neque, a nisi culpa magnam dolorum blanditiis pariatur cupiditate, ipsam natus libero officia ullam. Amet nihil eius blanditiis voluptate molestias, perspiciatis dicta harum hic nisi, explicabo porro vero iure architecto quia officia excepturi. Quos a tempora impedit nobis dolor esse aperiam dolores molestias possimus? Aliquid incidunt numquam, quidem est a dicta quaerat soluta totam voluptate. Esse deleniti, veniam rerum cupiditate placeat corporis laboriosam nostrum dolores maxime eum saepe iste, ipsum quam recusandae incidunt temporibus eos provident autem minima quia iusto amet maiores! Nulla culpa minima, adipisci alias, non totam quis accusamus, natus itaque soluta fugit recusandae pariatur dolor deleniti voluptatum ratione! Modi quam aliquam id? Optio, eos laboriosam! Suscipit autem nobis fuga ea libero consequuntur nihil modi, quis excepturi. Ut odit nam at ratione rem deleniti reprehenderit quia, minus quaerat ipsa in soluta temporibus neque eligendi maxime sapiente doloribus quae ipsum nulla sint impedit laudantium. Tempora, nobis! Magnam fugiat ratione voluptatibus accusamus, reiciendis in assumenda culpa impedit harum laboriosam eum. Voluptatem officia commodi ratione eius doloremque. Explicabo fugiat minus, iste consequatur suscipit eaque ipsa dolor ea illum nobis pariatur corrupti tempora perspiciatis a hic voluptatum optio sed!</p>
        </div>
        <Sider/>
    </div>
  )
}

export default single
