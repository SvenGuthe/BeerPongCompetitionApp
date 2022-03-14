import { tCompetitionPlayer } from "../../../../../types/competition";
import CompetitionPlayerDetailTable from "./CompetitionPlayerDetailTable";

const CompetitionPlayerDetail: React.FC<{
    competitionPlayerDetail: tCompetitionPlayer
}> = (props) => {

    const competitionPlayerDetail = props.competitionPlayerDetail;

    return <>
        {competitionPlayerDetail && <>
            <h6>{competitionPlayerDetail.user.gamerTag}</h6>
            <CompetitionPlayerDetailTable competitionPlayerDetail={competitionPlayerDetail} />
        </>}
    </>;

};

export default CompetitionPlayerDetail;