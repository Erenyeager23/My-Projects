import { useEffect, useState } from 'react'


function App() {
  const [anime,setanime] = useState([]);
  const [name,setname]=useState("");
  const [year,setYear]=useState();
  
  const addanime=async()=>{
    const animeData={
      name,
      year
    };
    try{
    const response=await fetch('http://127.0.0.1:8000/api/anime/create/',{
      method:"POST",
      headers:{
        'Content-Type':'application/json',
      },
      body:JSON.stringify(animeData),
    });

    const data=await response.json();
    setanime((prev)=>[...prev,data]);
  }catch(err){
      console.log(err);
    }
  }

  useEffect(()=>{
    fetchanime();
  },[])
  const fetchanime= async()=>{
    try{
      const response=await fetch('http://127.0.0.1:8000/api/anime/');
      const data=await response.json();
      setanime(data);
    }catch(err){
      console.log(err)
    }
    

  }

  return (
    <>
    <h1>Anime List</h1>
    <div>
      <input type="text" placeholder="Anime Name" onChange={(e)=>setname(e.target.value)}/>
      <input type="number" placeholder="Release Year" onChange={(e)=>setYear(e.target.value)} />
      <button onClick={addanime}>Add Anime</button>
    </div>
      {
        anime.map((anime)=>(
          <div>
            <p>Anime Name:{anime.name}</p>
            <p>Release Year:{anime.year}</p>
          </div>
        ))
      }
    </>
  )
}

export default App
