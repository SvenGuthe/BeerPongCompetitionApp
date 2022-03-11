import { useEffect, useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../store/combine-store";
import { addTeam } from "../../store/team/team-store";
import { tTeam } from "../../types/team";
import { getRequestWithID } from "../../utility/genericHTTPFunctions";
import RealTeamDetails from "./realteam/RealTeamDetails";

const TeamDetails: React.FC = () => {

    const [selectedTeam, setSelectedTeam] = useState<tTeam>();
    const dispatch = useDispatch();
    const id = useParams().id;

    const { teams } = useSelector((state: RootState) => {
        return {
            teams: state.team.teams
        };
    });

    useEffect(() => {

        if (id) {
            const team = teams?.find(team => team.id === +id);
            if (team) {
                setSelectedTeam(team)
            } else {
                dispatch(getRequestWithID(+id, "/team/team", addTeam));
            }
        }

    }, [teams, id, dispatch]);

    const detailComponent = useMemo(() => {
        return selectedTeam ? <RealTeamDetails team={selectedTeam} /> : <></>
    }, [selectedTeam]);

    return <>
        <h3>{selectedTeam?.teamName}</h3>
        {detailComponent}
    </>;
};

export default TeamDetails;