import { useEffect } from "react";
import { useParams } from "react-router-dom";

const Detail = () => {
    const {id} = useParams(); //url의 id를 라우터가 넘겨줌 (변수명 : link에서 설정한 변수명 ex.:id, :test)
    useEffect(async ()=>{
        const json = await(
            await fetch(`https://yts.mx/api/v2/list_movies.json?movie_id=${id}`)
        ).json();
        console.log(json);
    }, [])
    return <h1>Detail</h1>
};

export default Detail;