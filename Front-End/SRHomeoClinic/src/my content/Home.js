import React from 'react'
import {Link} from 'react-router-dom'
const Home = () => {
  const posts=[{
    id:1,
    title : 'Lorem ipsum dolor sit AnimatinEffect',
    desc :" Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus qui accusamus quaerat? Magnam soluta fugit hic suscipit quasi in aut.",
    img : "https://th.bing.com/th/id/R.ae87891b3dde7b28712b75d14342f1a5?rik=2ZT%2baXLkZYcxWg&riu=http%3a%2f%2fthewowstyle.com%2fwp-content%2fuploads%2f2015%2f01%2fnature-wallpaper-27.jpg&ehk=jIVFSOxLN%2fQKs4hEfZHNWAeXoeXkeEXooP%2fTy9Vwkek%3d&risl=&pid=ImgRaw&r=0"
  },
  { id:2,
    title : 'Lorem ipsum dolor sit AnimatinEffect',
    desc :" Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus qui accusamus quaerat? Magnam soluta fugit hic suscipit quasi in aut.",
    img : "https://th.bing.com/th/id/OIP.vNpR-4DYMRCXEEpfrAzLXwHaE8?rs=1&pid=ImgDetMain"},
    { id:3,
      title : 'Lorem ipsum dolor sit AnimatinEffect',
      desc :" Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus qui accusamus quaerat? Magnam soluta fugit hic suscipit quasi in aut.",
      img : "https://th.bing.com/th/id/R.3dfda9deac3b38b9edc14d0b778b567b?rik=47fkBaImwoLpmQ&riu=http%3a%2f%2fwww.pixelstalk.net%2fwp-content%2fuploads%2f2016%2f08%2fFree-Download-Wildlife-Image.jpg&ehk=0Y6qVq5JBhu1a00gxIhsITN9M9Ntlxqcz5hGyTTQRqw%3d&risl=&pid=ImgRaw&r=0"

    },
  ];
  return (
    <div className='home'>
        <div className="posts">
          {posts.map(post=>(
            <div className="post" key={post.id}>
              <div className="img">
                <img src={post.img} alt="" />
              </div>
              <div className="description">
                <Link className='link' to={`/post/${post.id}`}>
                <h1>{post.title}</h1>
                </Link>
                <p>{post.desc}</p>
                <button className='login-btn'>Read more</button>
              </div>
            </div>
          ))}
        </div>
    </div>
  )
}

export default Home
